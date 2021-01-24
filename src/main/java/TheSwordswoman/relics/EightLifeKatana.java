package TheSwordswoman.relics;

import TheSwordswoman.TheSwordswoman;
import TheSwordswoman.actions.RandomReverbToHandAction;

public class EightLifeKatana extends AbstractBlazRelic {
    public static final String ID = makeID("EightLifeKatana");

    public EightLifeKatana() {
        super(ID, RelicTier.RARE, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public void atTurnStartPostDraw() {
        atb(new RandomReverbToHandAction(1));
    }
}
