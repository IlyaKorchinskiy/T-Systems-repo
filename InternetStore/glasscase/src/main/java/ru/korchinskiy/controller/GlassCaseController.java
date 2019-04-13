package ru.korchinskiy.controller;

import ru.korchinskiy.ejb.ProductEJB;

import javax.ejb.EJB;
import javax.inject.Named;

@Named
public class GlassCaseController {

    @EJB
    private ProductEJB productEJB;
}
