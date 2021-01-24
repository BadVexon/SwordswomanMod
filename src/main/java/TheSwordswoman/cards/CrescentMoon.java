package TheSwordswoman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrescentMoon extends AbstractBlazCard {

    public final static String ID = makeID("CrescentMoon");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;

    public CrescentMoon() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        FleetingField.fleeting.set(this, true);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        atb(new FeedAction(m, makeInfo(), damage));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}