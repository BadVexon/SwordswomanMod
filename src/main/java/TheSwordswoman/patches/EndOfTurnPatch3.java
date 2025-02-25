package TheSwordswoman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.GameActionManager;


@SpirePatch(
        clz = GameActionManager.class,
        method = "clear"
)

public class EndOfTurnPatch3 {

    @SpirePrefixPatch
    public static SpireReturn Clear(GameActionManager m) {

        System.out.println("Setting bool to false");
        EndOfTurnPatch.damageTakenLastTurn = false;
        EndOfTurnPatch.damageTakenThisTurn = false;


        return SpireReturn.Continue();
    }

}