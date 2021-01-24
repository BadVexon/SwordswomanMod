package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HoumatsuMugenKochouji extends AbstractBlazCard {

    public final static String ID = makeID("HoumatsuMugenKochouji");

    //stupid intellij stuff ATTACK, ALL, RARE

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 2;

    public HoumatsuMugenKochouji() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        exhaust = true;
        isMultiDamage = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            if (q.getIntentBaseDmg() > -1) {
                blck();
            } else {
                allDmg(AbstractGameAction.AttackEffect.FIRE);
            }
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
        upgradeBaseCost(1);
    }
}