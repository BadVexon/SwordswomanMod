package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Crosscut extends AbstractBlazCard {

    public final static String ID = makeID("Crosscut");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public Crosscut() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        blck();
        blck();
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void abc(AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        blck();
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }
}