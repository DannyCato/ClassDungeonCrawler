package edu.rit.swen262.service;

/**
 * A enum which defines the current menu the UI is in, transitioning to
 * a sub-menu or cycling back to the default one based upon what keystroke
 * is inputted
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
                    break;
                case 'a':
                    p.setMenu(ATTACK);
                    break;
                case 'i':
                    p.setMenu(INVENTORY);
                    break;
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
            p.setMenu(BAG);
        }
    },
    BAG {
        @Override
        public void handleInput(InputParser p, char keystroke) {
            p.setMenu(ITEM);
        }
    },
    ITEM {
        @Override
        public void handleInput(InputParser p, char keystroke) {
            p.setMenu(DEFAULT);
        }
    };
    public abstract void handleInput(InputParser p, char keystroke);
}