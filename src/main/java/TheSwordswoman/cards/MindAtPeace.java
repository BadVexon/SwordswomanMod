package TheSwordswoman.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.MindAtPeacePower;

public class MindAtPeace extends AbstractBlazCard {

    public final static String ID = makeID("MindAtPeace");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int BLOCK = 20;
    private static final int UPG_BLOCK = 5;

    public MindAtPeace() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new MindAtPeacePower());
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}