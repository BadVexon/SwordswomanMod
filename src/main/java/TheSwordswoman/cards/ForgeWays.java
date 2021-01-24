package TheSwordswoman.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ForgeWays extends AbstractBlazCard {

    public final static String ID = makeID("ForgeWays");

    //stupid intellij stuff ATTACK, ALL_ENEMY, RARE

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 5;

    private static final int MAGIC = 1;

    public ForgeWays() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 3;
        AutoplayField.autoplay.set(this, true);
        isMultiDamage = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        shuffleIn(this.makeStatEquivalentCopy(), magicNumber);
        for (int i = 0; i < reverb(); i++)
            atb(new DamageAction(p, new DamageInfo(p, silly, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeSilly(-1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}