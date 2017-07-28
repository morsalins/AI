package ai.minimax;

import java.util.ArrayList;

/**
 * Created by morsalin on 7/24/17.
 */
public interface MiniMaxNode {
    //Terminal Test.
    public boolean isTerminal();

    //Calculate and Terminal utility.
    public int getTerminalUtility();

    //Get utility value.
    public int getUtility();

    //Set utility value.
    public void setUtility(int utility);

    //Get all the children for max node.
    public ArrayList<MiniMaxNode> getChildrenOfMax();

    //Get all the children for min node.
    public ArrayList<MiniMaxNode> getChildrenOfMin();
}
