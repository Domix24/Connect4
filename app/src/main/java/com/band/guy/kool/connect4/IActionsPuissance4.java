package com.band.guy.kool.connect4;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Interface qui implemente les actions
 */
public interface IActionsPuissance4 {
    public Joueur avoirJoueur(int ligne, int colonne);
    public int jouer(int colonne);
    public int jouerIA();
}
