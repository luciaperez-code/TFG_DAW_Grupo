import { ChangeDetectorRef, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currentIndex: number = 0;
  slides: string[] = ['El planeta de los simios', 'Garfield', 'Cazafantasmas', 'Civil War', 'El Especialista', 'Godzila vs Kong'];
  
  constructor(private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {}

  nextSlide(): void {
    console.log("Nextslide antes: " + this.currentIndex);
    this.currentIndex = (this.currentIndex + 1) % this.slides.length;
    this.cdr.detectChanges();
    console.log("Nextslide después: " + this.currentIndex);
  }

  prevSlide(): void {
    this.currentIndex = (this.currentIndex - 1 + this.slides.length) % this.slides.length;
    this.cdr.detectChanges();
  }
}

