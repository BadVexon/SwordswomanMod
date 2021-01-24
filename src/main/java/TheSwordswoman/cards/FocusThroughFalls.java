package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FocusThroughFalls extends AbstractBlazCard {

    public final static String ID = makeID("FocusThroughFalls");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 9;

    public FocusThroughFalls() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(1, false, false, false));
        blck();
        for (int i = 0; i < reverb(); i++) {
            blck();
        }
    }

    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}