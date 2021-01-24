package TheSwordswoman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


@SpirePatch(
        clz = AbstractPlayer.class,
        method = "updateCardsOnDamage"
)

public class EndOfTurnPatch2 {

    @SpirePrefixPatch
    public static SpireReturn Damaged(AbstractPlayer p) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            System.out.println("Just took damage, setting bool to true.");
            EndOfTurnPatch.damageTakenThisTurn = true;
        }


        return SpireReturn.Continue();
    }

}