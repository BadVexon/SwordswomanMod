package TheSwordswoman.powers;

import TheSwordswoman.BlazMod;
import TheSwordswoman.stances.DawnflyStance;
import TheSwordswoman.stances.FreeflutterStance;
import TheSwordswoman.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class HollowStonePower extends TwoAmountPower {
    public static final String POWER_ID = BlazMod.makeID("HollowStonePower");
    private static final Texture tex84 = TextureLoader.getTexture("blazmodResources/images/powers/hs84.png");
    private static final Texture tex32 = TextureLoader.getTexture("blazmodResources/images/powers/HS32.png");

    private int originalAmt2;

    public HollowStonePower(final int amount, final int amount2) {
        name = "Revolving Phoenix";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.amount2 = amount2;
        originalAmt2 = amount2;

        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                amount2 = originalAmt2;
            }
        });
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (amount2 > 0) {
            amount2--;
            if (amount2 == 0) {
                flash();
                addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        if (AbstractDungeon.player.stance instanceof DawnflyStance) {
                            addToTop(new ChangeStanceAction(FreeflutterStance.STANCE_ID));
                        } else if (AbstractDungeon.player.stance instanceof FreeflutterStance) {
                            addToTop(new ChangeStanceAction(DawnflyStance.STANCE_ID));
                        }
                    }
                });
            }
        }
    }


    @Override
    public void updateDescription() {
        description = "Whenever you play #b" + originalAmt2 + " cards in a turn, gain #b" + amount + " #yStrength and #ySwap. #b" + amount2 + " remaining this turn.";
    }
}
