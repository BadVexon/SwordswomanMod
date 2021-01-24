package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.ReflectionPower;

public class MyEverything extends AbstractBlazCard {

    public final static String ID = makeID("MyEverything");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 2;

    public MyEverything() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new ReflectionPower(1));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}