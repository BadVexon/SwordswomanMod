package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EightLifePilgrimage extends AbstractBlazCard {

    public final static String ID = makeID("EightLifePilgrimage");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 8;
    private static final int BLOCK = 8;

    public EightLifePilgrimage() {
        super(ID, 4, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new HealAction(p, p, 8));
        blck();
        atb(new DrawCardAction(8));
        applyToSelf(new StrengthPower(p, 8));
    }

    public void upp() {
        upgradeBaseCost(3);
    }
}