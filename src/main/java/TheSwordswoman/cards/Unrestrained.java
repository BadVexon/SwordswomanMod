package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.stances.FreeflutterStance;

public class Unrestrained extends AbstractBlazCard {

    public final static String ID = makeID("Unrestrained");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 4;

    private static final int BLOCK = 15;
    private static final int UPG_BLOCK = 4;

    public Unrestrained() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            if (p.stance.ID.equals(FreeflutterStance.STANCE_ID) && m.currentBlock > 0) {
                cantUseMessage = "This enemy is too guarded.";
                return false;
            }
        return super.canUse(p, m);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
        swapStance();
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        blck();
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void abc(AbstractMonster m) {
        dawnfly(m);
        freeflutter(m);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }
}