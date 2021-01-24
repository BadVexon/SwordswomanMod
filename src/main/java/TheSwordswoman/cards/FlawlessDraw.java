package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import TheSwordswoman.patches.EndOfTurnPatch;
import TheSwordswoman.powers.ButterflyFormPower;
import TheSwordswoman.powers.GlasswingWaltzPower;
import TheSwordswoman.stances.FreeflutterStance;

public class FlawlessDraw extends AbstractBlazCard {

    public final static String ID = makeID("FlawlessDraw");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;

    public FlawlessDraw() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseSecondDamage = secondDamage = 15;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        boolean s = selfRetain;
        if ((AbstractDungeon.player.stance.ID.equals(FreeflutterStance.STANCE_ID) || AbstractDungeon.player.hasPower(ButterflyFormPower.POWER_ID) || AbstractDungeon.player.hasPower(GlasswingWaltzPower.POWER_ID)) && upgraded) {
            selfRetain = true;
        } else {
            selfRetain = s;
        }
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        altdmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (!EndOfTurnPatch.damageTakenLastTurn) {
            altdmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        blck();
    }

    @Override
    public void abc(AbstractMonster m) {
        altdmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        blck();
        if (!EndOfTurnPatch.damageTakenLastTurn) {
            altdmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            blck();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();// 39

        if (!EndOfTurnPatch.damageTakenLastTurn && (AbstractDungeon.player.stance.ID.equals(FreeflutterStance.STANCE_ID) || AbstractDungeon.player.hasPower(ButterflyFormPower.POWER_ID) || AbstractDungeon.player.hasPower(GlasswingWaltzPower.POWER_ID))) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();// 42
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }
}