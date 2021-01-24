package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.NextTurnBufferPower;

public class Lockswords extends AbstractBlazCard {

    public final static String ID = makeID("Lockswords");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    public Lockswords() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new NextTurnBufferPower(1));
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}