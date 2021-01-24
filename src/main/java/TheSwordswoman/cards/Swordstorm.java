package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Swordstorm extends AbstractBlazCard {

    public final static String ID = makeID("Swordstorm");

    //stupid intellij stuff ATTACK, ALL_ENEMY, COMMON

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 1;

    public Swordstorm() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        for (int i = 0; i < reverb(); i++) {
            allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}