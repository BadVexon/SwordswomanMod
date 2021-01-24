package TheSwordswoman.cards;

import TheSwordswoman.powers.HollowStonePower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DrippingWaterHollowsStone extends AbstractBlazCard {

    public final static String ID = makeID("DrippingWaterHollowsStone");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 7;

    public DrippingWaterHollowsStone() {
        super(ID, 0, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HollowStonePower(1, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(-2);
    }
}