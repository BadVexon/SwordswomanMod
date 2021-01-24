package TheSwordswoman.relics;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import TheSwordswoman.TheSwordswoman;

public class Venustas extends AbstractBlazRelic {
    public static final String ID = makeID("Venustas");

    public Venustas() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void atTurnStartPostDraw() {
        flash();
        atb(new DrawCardAction(1));
        atb(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, false));
    }

    @Override
    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        atTurnStartPostDraw();
    }
}
