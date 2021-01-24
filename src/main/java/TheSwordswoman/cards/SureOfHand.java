package TheSwordswoman.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SureOfHand extends AbstractBlazCard {

    public final static String ID = makeID("SureOfHand");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SureOfHand() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        addToBot(new SelectCardsAction(p.hand.group, 1, "Choose cards to discard to return cards.", false, c -> !c.cardID.equals(this.cardID), list -> list.forEach(c ->
                {
                    addToTop(new BetterDiscardPileToHandAction(magicNumber));
                    addToTop(new DiscardSpecificCardAction(c));
                }
        )));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}