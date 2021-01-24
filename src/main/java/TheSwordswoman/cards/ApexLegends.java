package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import TheSwordswoman.actions.XCostAction;

public class ApexLegends extends AbstractBlazCard {

    public final static String ID = makeID("ApexLegends");

    //stupid intellij stuff ATTACK, NONE, UNCOMMON

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;

    public ApexLegends() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        addToBot(new XCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; ++i) {
                allDmg(AbstractGameAction.AttackEffect.FIRE);
            }
            return true;
        }));
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        addToBot(new XCostAction(this, (effect, params) -> {
            att(new DrawCardAction(effect));
            if (upgraded) {
                att(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new EquilibriumPower(AbstractDungeon.player, 1), 1));
            }
            return true;
        }));
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