package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ButterflyEffect extends AbstractBlazCard {

    public final static String ID = makeID("ButterflyEffect");

    //stupid intellij stuff SKILL, SELF, BASIC

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = -1;

    public ButterflyEffect() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        swapStance();
        applyToSelf(new StrengthPower(p, 1));
        applyToSelf(new WeakPower(p, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}