package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.powers.NoDewPower;

public class CastAwayDew extends AbstractBlazCard {

    public final static String ID = makeID("CastAwayDew");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMON

    public CastAwayDew() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        applyToSelf(new NoDewPower(5));
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        atb(new RemoveAllBlockAction(m, AbstractDungeon.player));
    }

    @Override
    public void abc(AbstractMonster m) {
        freeflutter(m);
        dawnfly(m);
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}