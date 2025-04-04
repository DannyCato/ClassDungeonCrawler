package edu.rit.swen262.service;

import java.util.Random;

/**
 * Singleton class to ensure that all classes 
 */
public class RandomInstance {
    private static Random RAND;
    private static Integer SEED ;


    public static void seed(int seed) {
        SEED = seed ;
    }

    public static Random instance() {
        if (SEED == null) {
            SEED = new Random().nextInt() ;
        }
        if (RAND == null) {
            RAND = new Random(SEED) ;
        }
        return RAND ;
    }
}
