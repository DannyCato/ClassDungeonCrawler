package edu.rit.swen262.service;

import java.util.ArrayList;
import java.util.List;

/**
 * A enum which 
 * 
 * @author Victor Bovat
 */
public enum MenuState {
    DEFAULT {
        @Override
        public void handleInput(InputParser p, char keystroke) {
            switch(keystroke) {
                case 'm':
                    p.setMenu(MOVE);
                case 'a':
                    p.setMenu(ATTACK);
                case 'i':
                    p.setMenu(INVENTORY);
                default:
                    break;
            }
        }
    },
    MOVE {
        @Override
        public void handleInput(InputParser p, char keystroke) {
            p.setMenu(DEFAULT);
        }
    },
    ATTACK {
        @Override
        public void handleInput(InputParser p, char keystroke) {
            p.setMenu(DEFAULT);
        }
    },
    INVENTORY {
        @Override
        public void handleInput(InputParser p, char keystroke) {
            p.setMenu(DEFAULT);
        }
    };
    public abstract void handleInput(InputParser p, char keystroke);
}