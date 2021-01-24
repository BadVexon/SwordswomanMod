package TheSwordswoman.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import TheSwordswoman.BlazMod;
import TheSwordswoman.powers.ButterflyFormPower;
import TheSwordswoman.powers.GlasswingWaltzPower;
import TheSwordswoman.stances.DawnflyStance;
import TheSwordswoman.stances.FreeflutterStance;

public class ButterFlutter extends AbstractBlazCard {

    public final static String ID = makeID("ButterFlutter");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 14;
    private static final int BLOCK = 14;

    public ButterFlutter() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        tags.add(BlazMod.TRANCE);
    }

    public void us(AbstractPlayer p, AbstractMonster m) {
        stanceCheck(m);
    }

    @Override
    public void dawnfly(AbstractMonster m) {
        blck();
    }

    @Override
    public void freeflutter(AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void trance(AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (AbstractDungeon.player.hasPower(GlasswingWaltzPower.POWER_ID) || AbstractDungeon.player.hasPower(ButterflyFormPower.POWER_ID)) {
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractDungeon.player.loseBlock(AbstractDungeon.player.currentBlock / 2);
                        }
                    });
                    att(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, 1), 1));
                } else if (AbstractDungeon.player.stance instanceof DawnflyStance) {
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            isDone = true;
                            AbstractDungeon.player.loseBlock(AbstractDungeon.player.currentBlock / 2);
                        }
                    });
                } else if (AbstractDungeon.player.stance instanceof FreeflutterStance) {
                    att(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, 1), 1));
                }
            }
        });
    }

    @Override
    public void abc(AbstractMonster m) {
        dawnfly(m);
        freeflutter(m);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}