package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;

public class InLoveAndWar extends AbstractBlazCard {

    public final static String ID = makeID("InLoveAndWar");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 4;

    public InLoveAndWar() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToSelf(new DoubleTapPower(p, 1));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}