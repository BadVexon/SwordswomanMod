package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ButterflyBlade extends AbstractBlazCard {

    public final static String ID = makeID("ButterflyBlade");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 2;

    public ButterflyBlade() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        selfRetain = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
        swapStance();
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        blck();
    }

    @Override
    public void abc(AbstractMonster m) {
        super.abc(m);
        dawnfly(m);
        freeflutter(m);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }
}