package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Dominic
 */
public interface IÉvènementsPuissance4 {
    public String aGagné(Joueur joueur);
    public String grillePleine();
    public String auTourDe(Joueur joueur);
    public String positionDéjàOccupée(int ligne, int colonne);
}