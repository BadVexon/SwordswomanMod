package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import TheSwordswoman.TheSwordswoman;

public class ConfectionCutter extends AbstractBlazRelic {
    public static final String ID = makeID("ConfectionCutter");

    public ConfectionCutter() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void atTurnStart() {
        counter = 0;
        grayscale = false;
    }

    @Override
    public void onManualDiscard() {
        if (counter < 5) counter++;
        if (counter == 5) {
            flash();
            grayscale = true;
            addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
    }
}
