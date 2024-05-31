import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LineaPedidoDTO } from 'src/app/beans/LineaPedidoDTO';
import { Projection } from 'src/app/beans/Projection';
import { CarritoService } from 'src/app/services/carrito.service';
import { DateService } from 'src/app/services/date.service';
import { ProjectionService } from 'src/app/services/projection.service';

@Component({
  selector: 'app-projection-detail',
  templateUrl: './projection-detail.component.html',
  styleUrls: ['./projection-detail.component.css']
})
export class ProjectionDetailComponent implements OnInit {

  projectionId!: number;
  projection: any;
  seatRows: number[][] = [];
  selectedNormalSeats: { row: number, seat: number }[] = [];
  selectedSpecialSeats: { row: number, seat: number }[] = [];
  projectionDate: string = '';
  itemToCart : LineaPedidoDTO | null = null;
  quantitySeats: number = 0;
  token: string | null = sessionStorage.getItem('token');

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectionService: ProjectionService,
    private dateService : DateService,
    private carritoService : CarritoService
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.projectionId = +params['id'];
      this.loadProjectionDetails();
    });
  }

  loadProjectionDetails() {
    this.projectionService.getProjectionById(this.projectionId).subscribe(response => {
      this.projection = response.successResponse;
      this.initializeSeats();
      this.projectionDate = this.dateService.formatDateString(new Date(this.projection.date));
      this.projectionDate = this.dateService.formatDateShort(new Date(this.projection.date));
    });
  }

  initializeSeats() {
    const totalNormalSeats = +this.projection.screen.normalSeats;
    const totalSpecialSeats = +this.projection.screen.specialSeats;
  
    let normalSeatsPerRow = 10;
    let specialSeatsPerRow = totalSpecialSeats;
  
    // First row is for special seats
    this.seatRows.push(new Array(specialSeatsPerRow).fill(0));
  
    // Remaining rows for normal seats
    for (let i = 0; i < Math.ceil(totalNormalSeats / normalSeatsPerRow); i++) {
      this.seatRows.push(new Array(normalSeatsPerRow).fill(0));
    }
  }

  selectSeat(rowIndex: number, seatIndex: number) {
    if (this.getSeatClass(rowIndex, seatIndex).occupied) {
      return; // Do not allow selection of occupied seats
    }

    const seat = { row: rowIndex, seat: seatIndex };

    if (rowIndex === 0) {
      const seatPos = this.selectedSpecialSeats.findIndex(s => s.row === rowIndex && s.seat === seatIndex);
      if (seatPos > -1) {
        this.selectedSpecialSeats.splice(seatPos, 1);
        this.quantitySeats--;
      } else {
        this.selectedSpecialSeats.push(seat);
        this.quantitySeats++;
      }
    } else {
      const seatPos = this.selectedNormalSeats.findIndex(s => s.row === rowIndex && s.seat === seatIndex);
      if (seatPos > -1) {
        this.selectedNormalSeats.splice(seatPos, 1);
        this.quantitySeats--;
      } else {
        this.selectedNormalSeats.push(seat);
        this.quantitySeats++;
      }
    }
  }

  getSeatClass(rowIndex: number, seatIndex: number) {
    let occupied = false;
    let selected = false;
    if (rowIndex === 0) {
      // Special seats
      occupied = JSON.parse(this.projection.occupiedSpecialSeats)[seatIndex] === 1;
      selected = this.selectedSpecialSeats.some(seat => seat.row === rowIndex && seat.seat === seatIndex);
    } else {
      // Normal seats
      const normalSeatIndex = (rowIndex - 1) * 10 + seatIndex;
      occupied = JSON.parse(this.projection.occupiedNormalSeats)[normalSeatIndex] === 1;
      selected = this.selectedNormalSeats.some(seat => seat.row === rowIndex && seat.seat === seatIndex);
    }
    return {
      seat: true,
      occupied: occupied,
      selected: selected,
      special: rowIndex === 0,
      'special-selected': rowIndex === 0 && selected,
      'occupied-special': rowIndex === 0 && occupied
    };
  }


  addCart() {
    const occupiedNormalSeats = JSON.parse(this.projection.occupiedNormalSeats);
    const occupiedSpecialSeats = JSON.parse(this.projection.occupiedSpecialSeats);
    if (this.token != null){
      this.selectedNormalSeats.forEach(seat => {
        const normalSeatIndex = (seat.row - 1) * 10 + seat.seat;
        occupiedNormalSeats[normalSeatIndex] = 1;
      });
  
      this.selectedSpecialSeats.forEach(seat => {
        occupiedSpecialSeats[seat.seat] = 1;
      });
  
      this.projection.occupiedNormalSeats = JSON.stringify(occupiedNormalSeats);
      this.projection.occupiedSpecialSeats = JSON.stringify(occupiedSpecialSeats);
      console.log('Normal seats:', this.projection.occupiedNormalSeats);
      console.log('Special seats:', this.projection.occupiedSpecialSeats);
  
      if (this.projection != null){
        this.itemToCart = {
          idProjection : this.projection.idProjection,
          quantity: this.quantitySeats,
          price: this.projection.price,
          occupiedNormalSeats: this.projection.occupiedNormalSeats,
          occupiedSpecialSeats: this.projection.occupiedSpecialSeats
        };
        console.log(this.itemToCart);
      }
      if (this.itemToCart != null){
        this.carritoService.addToCart(this.itemToCart);
        this.router.navigate(['/cart']);
      }
    }else {
      window.alert('No puedes añadir cosas al carrito sin iniciar sesión :(');
    }
    
  }

}
