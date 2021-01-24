package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlindingFlash extends AbstractBlazCard {

    public final static String ID = makeID("BlindingFlash");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    public BlindingFlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        weaken(m, 3);
        if (upgraded) atb(new DrawCardAction(1));
        if (reverb() > 0)
            expose(m, reverb());
    }

    public void upp() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}