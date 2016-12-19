/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RPIS41.Neizvestny.wdad.learn.rmi;

import java.util.*;
import java.io.Serializable;
/**
 *
 * @author Cyfralus
 */
public class Tariffs implements Serializable{
    static final HashMap<String, Double> values = new HashMap<>();
    static final String COLDWATER_KEY = "coldwater";
    static final String HOTWATER_KEY = "hotwater";
    static final String ELECTRICITY_KEY = "electricity";
    static final String GAS_KEY = "gas";
}
