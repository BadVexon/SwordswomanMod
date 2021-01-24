package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class SidewaySolace extends AbstractBlazCard {

    public final static String ID = makeID("SidewaySolace");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 4;

    public SidewaySolace() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, magicNumber));
        applyToSelf(new VulnerablePower(p, 2, false));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}