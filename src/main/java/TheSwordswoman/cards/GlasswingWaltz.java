package TheSwordswoman.cards;

import TheSwordswoman.powers.GlasswingWaltzPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GlasswingWaltz extends AbstractBlazCard {

    public final static String ID = makeID("GlasswingWaltz");

    //stupid intellij stuff SKILL, SELF, RARE

    public GlasswingWaltz() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GlasswingWaltzPower(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}