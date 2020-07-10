class Complex {

    double real;
    double image;


    // write methods here
    void add(Complex num) {
        real = this.real + num.real;
        image = this.image + num.image;
    }
    void subtract(Complex num) {
        real = this.real - num.real;
        image = this.image - num.image;

    }
}