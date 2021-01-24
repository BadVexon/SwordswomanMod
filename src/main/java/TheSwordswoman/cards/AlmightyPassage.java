package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AlmightyPassage extends AbstractBlazCard {

    public final static String ID = makeID("AlmightyPassage");

    //stupid intellij stuff ATTACK, RARE, ALL_ENEMY

    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 2;

    private static final int MAGIC = 20;
    private static final int UPG_MAGIC = 5;

    public AlmightyPassage() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        for (AbstractMonster q : monsterList()) {
            atb(new JudgementAction(q, magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
    }
}