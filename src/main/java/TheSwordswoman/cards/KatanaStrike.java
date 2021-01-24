package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class KatanaStrike extends AbstractBlazCard {

    public final static String ID = makeID("KatanaStrike");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;

    public KatanaStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseSecondDamage = secondDamage = 10;
        tags.add(CardTags.STRIKE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            atb(new DamageAction(q, makeInfo(), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (upgraded) {
            applyToSelf(new StrengthPower(AbstractDungeon.player, 1));
        }
    }

    @Override
    public void abc(AbstractMonster m) {
        dawnfly(m);
        freeflutter(m);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}