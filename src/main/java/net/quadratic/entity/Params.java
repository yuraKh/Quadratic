package net.quadratic.entity;

import net.quadratic.dto.ParamsDto;

import javax.persistence.*;

@Entity
@Table(name = "params")
public class Params {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "a", nullable = false)
    double a;

    @Column(name = "b", nullable = false)
    double b;

    @Column(name = "c", nullable = false)
    double c;

    @Column(name = "x1", nullable = true)
    Double x1;

    @Column(name = "x2", nullable = true)
    Double x2;

    public Params(ParamsDto dto) {
        this.a = dto.getA();
        this.b = dto.getB();
        this.c = dto.getC();
    }

    public Params() {

    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }
}
