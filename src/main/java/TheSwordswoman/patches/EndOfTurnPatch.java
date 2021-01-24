package TheSwordswoman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;


@SpirePatch(
        clz = AbstractPlayer.class,
        method = "applyStartOfTurnPreDrawCards"
)

public class EndOfTurnPatch {
    public static boolean damageTakenLastTurn = false;
    public static boolean damageTakenThisTurn = false;

    @SpirePrefixPatch
    public static SpireReturn EOT(AbstractPlayer p) {
        if (damageTakenThisTurn == true) {
            System.out.println("Setting bool to true, damage was taken last turn");
            damageTakenLastTurn = true;
        } else {
            System.out.println("Setting bool to false, damage was not taken last turn");
            damageTakenLastTurn = false;
        }


        damageTakenThisTurn = false;

        return SpireReturn.Continue();
    }

}