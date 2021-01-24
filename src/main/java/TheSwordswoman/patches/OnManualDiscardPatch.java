package TheSwordswoman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.powers.BladeGodPower;
import TheSwordswoman.powers.ClearTheMindPower;
import TheSwordswoman.powers.NoDewPower;

@SpirePatch(cls = "com.megacrit.cardcrawl.actions.GameActionManager", method = "incrementDiscard")
public class OnManualDiscardPatch {

    public static void Postfix(final boolean endOfTurn) {
        if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
            if (AbstractDungeon.player.hasPower(BladeGodPower.POWER_ID)) {
                AbstractPower p = AbstractDungeon.player.getPower(BladeGodPower.POWER_ID);
                p.flash();
                p.onSpecificTrigger();
            }
            if (AbstractDungeon.player.hasPower(ClearTheMindPower.POWER_ID)) {
                AbstractPower p = AbstractDungeon.player.getPower(ClearTheMindPower.POWER_ID);
                p.flash();
                p.onSpecificTrigger();
            }
            if (AbstractDungeon.player.hasPower(NoDewPower.POWER_ID)) {
                AbstractPower p = AbstractDungeon.player.getPower(NoDewPower.POWER_ID);
                p.flash();
                p.onSpecificTrigger();
            }
        }
    }

}