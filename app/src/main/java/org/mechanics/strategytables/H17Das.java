package org.mechanics.strategytables;

public class H17Das implements StrategyTable{

    public static final String[][] hardTable = {
            {"x","x","x","x","x","x","x","x","x","x","x","x"}, // 0 (impossible, placeholder)
            {"x","x","x","x","x","x","x","x","x","x","x","x"}, // 1 (impossible, placeholder)
            {"x","x","h","h","h","h","h","h","h","h","h","h"}, // 2
            {"x","x","h","h","h","h","h","h","h","h","h","h"}, // 3
            {"x","x","h","h","h","h","h","h","h","h","h","h"}, // 4
            {"x","x","h","h","h","h","h","h","h","h","h","h"}, // 5
            {"x","x","h","h","h","h","h","h","h","h","h","h"}, // 6
            {"x","x","h","h","h","h","h","h","h","h","h","h"}, // 7
            {"x","x","h","h","h","d","d","h","h","h","h","h"}, // 8
            {"x","x","d","d","d","d","d","h","h","h","h","h"}, // 9
            {"x","x","d","d","d","d","d","d","d","d","h","h"}, // 10
            {"x","x","d","d","d","d","d","d","d","d","d","d"}, // 11
            {"x","x","h","h","s","s","s","h","h","h","h","h"}, // 12
            {"x","x","s","s","s","s","s","h","h","h","h","h"}, // 13
            {"x","x","s","s","s","s","s","h","h","h","h","h"}, // 14
            {"x","x","s","s","s","s","s","h","h","h","s","h"}, // 15
            {"x","x","s","s","s","s","s","h","h","s","s","s"}, // 16
            {"x","x","s","s","s","s","s","s","s","s","s","s"}, // 17
            {"x","x","s","s","s","s","s","s","s","s","s","s"}, // 18
            {"x","x","s","s","s","s","s","s","s","s","s","s"}, // 19
            {"x","x","s","s","s","s","s","s","s","s","s","s"}, // 20
            {"x","x","s","s","s","s","s","s","s","s","s","s"}  // 21
    };

    public static final String[][] softTable = {
            {"x","x","x","x","x","x","x","x","x","x","x","x"}, // A-0 (impossible, placeholder)
            {"x","x","x","x","x","x","x","x","x","x","x","x"}, // A-1 (impossible, placeholder)
            {"x","x","h","h","h","d","d","h","h","h","h","h"}, // A-2
            {"x","x","h","h","h","d","d","h","h","h","h","h"}, // A-3
            {"x","x","h","h","d","d","d","h","h","h","h","h"}, // A-4
            {"x","x","h","h","d","d","d","h","h","h","h","h"}, // A-5
            {"x","x","h","d","d","d","d","h","h","h","h","h"}, // A-6
            {"x","x","d","d","d","d","d","s","s","h","h","h"}, // A-7
            {"x","x","s","s","s","s","d","s","s","s","s","s"}, // A-8
            {"x","x","s","s","s","s","s","s","s","s","s","s"}, // A-9
            {"x","x","s","s","s","s","s","s","s","s","s","s"} // A-10
    };

    public static final String[][] splitTable = {
            {"x","x","x","x","x","x","x","x","x","x","x","x"}, // 0-0 (impossible, placeholder)
            {"x","x","p","p","p","p","p","p","p","p","p","p"}, // 1-1 (same as A-A)
            {"x","x","p","p","p","p","p","p","h","h","h","h"}, // 2-2
            {"x","x","p","p","p","p","p","p","h","h","h","h"}, // 3-3
            {"x","x","h","h","h","p","p","h","h","h","h","h"}, // 4-4
            {"x","x","d","d","d","d","d","d","d","d","h","h"}, // 5-5
            {"x","x","p","p","p","p","p","h","h","h","h","h"}, // 6-6
            {"x","x","p","p","p","p","p","p","h","h","h","h"}, // 7-7
            {"x","x","p","p","p","p","p","p","p","p","p","p"}, // 8-8
            {"x","x","p","p","p","p","p","s","p","p","s","s"}, // 9-9
            {"x","x","s","s","s","s","s","s","s","s","s","s"}, // 10-10
            {"x","x","p","p","p","p","p","p","p","p","p","p"}  // A-A
    };
}