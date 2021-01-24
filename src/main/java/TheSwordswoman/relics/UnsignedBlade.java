package TheSwordswoman.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import TheSwordswoman.TheSwordswoman;

public class UnsignedBlade extends AbstractBlazRelic implements OnApplyPowerRelic {
    public static final String ID = makeID("UnsignedBlade");

    public UnsignedBlade() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, TheSwordswoman.Enums.SWORDSWOMAN_COLOR);
    }

    @Override
    public boolean onApplyPower(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {
        if (target == source && target == AbstractDungeon.player) {
            flash();
            addToBot(new GainBlockAction(AbstractDungeon.player, 5));
        }
        return true;
    }
}
