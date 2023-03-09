package it.niedermann.nextcloud.deck.ui.accountswitcher;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import it.niedermann.android.util.DimensionUtil;
import it.niedermann.nextcloud.deck.R;
import it.niedermann.nextcloud.deck.databinding.ItemAccountChooseBinding;
import it.niedermann.nextcloud.deck.model.Account;
import it.niedermann.nextcloud.deck.ui.theme.ThemeUtils;

public class AccountSwitcherViewHolder extends RecyclerView.ViewHolder {

    private final ItemAccountChooseBinding binding;

    public AccountSwitcherViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ItemAccountChooseBinding.bind(itemView);
    }

    public void bind(@NonNull Account account, @NonNull Consumer<Account> onAccountClick) {
        binding.accountName.setText(
                TextUtils.isEmpty(account.getUserDisplayName())
                        ? account.getUserName()
                        : account.getUserDisplayName()
        );
        binding.accountHost.setText(Uri.parse(account.getUrl()).getHost());
        Glide.with(itemView.getContext())
                .load(account.getAvatarUrl(DimensionUtil.INSTANCE.dpToPx(binding.accountItemAvatar.getContext(), R.dimen.avatar_size)))
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_account_circle_24)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.accountItemAvatar);
        itemView.setOnClickListener((v) -> onAccountClick.accept(account));
        binding.delete.setVisibility(View.GONE);

        final var utils = ThemeUtils.of(account.getColor(), itemView.getContext());
        utils.deck.themeSelectedCheck(binding.currentAccountIndicator.getContext(), binding.currentAccountIndicator.getDrawable());
    }
}
