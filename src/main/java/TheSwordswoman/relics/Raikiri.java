package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.TheSwordswoman;

public class Raikiri extends AbstractBlazRelic {
    public static final String ID = makeID("Raikiri");

    public Raikiri() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void atBattleStart() {
        counter = 0;
        grayscale = false;
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (!grayscale) {
            if (counter < 3)
                counter++;
            if (counter == 3) {
                flash();
                atb(new RemoveDebuffsAction(AbstractDungeon.player));
                grayscale = true;
            }
        }
    }

    @Override
    public void onVictory() {
        counter = -1;
    }
}
