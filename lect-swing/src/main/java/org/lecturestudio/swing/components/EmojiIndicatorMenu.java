package org.lecturestudio.swing.components;

import org.lecturestudio.core.model.EmojiType;
import org.lecturestudio.swing.AwtResourceLoader;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuUI;
import java.awt.*;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmojiIndicatorMenu extends JMenu {
	private final Map<EmojiType, EmojiMenuItem> emojiMenuItems = new LinkedHashMap<>();

	private final Map<EmojiType, Timer> emojiRemoveTimers = new EnumMap<>(EmojiType.class);

	private static final String EMOJI_PATH = "emojis/";

	private static final int EMOJI_COUNT_RESET_MILLIS = 20000;

	private static final Icon PLACEHOLDER_ICON = AwtResourceLoader.getIcon(EMOJI_PATH + "emoji-smile.svg", 20);

	public EmojiIndicatorMenu() {
		setIcon(PLACEHOLDER_ICON);
		setFocusable(false);
		setEnabled(false);
		setUI(new EmojiIndicatorMenuUI());
	}

	public synchronized void incrementEmojiCount(EmojiType emojiType) {
		final EmojiMenuItem emojiMenuItem = emojiMenuItems.get(emojiType);

		if (emojiMenuItem != null) {
			emojiMenuItem.incrementCount();

			if (emojiMenuItems.size() == 1) {
				setText(emojiMenuItem.getText());
			}

			emojiRemoveTimers.get(emojiType).restart();
			return;
		}

		final Icon icon = getIconByType(emojiType);
		final EmojiMenuItem menuItem = new EmojiMenuItem(icon);

		if (emojiMenuItems.isEmpty()) {
			setIcon(icon);
			setText(menuItem.getText());
		} else {
			if (emojiMenuItems.size() == 1) {
				showDropDown();
			}

			add(emojiMenuItems.entrySet().iterator().next().getValue());
			add(menuItem);
		}

		emojiMenuItems.put(emojiType, menuItem);

		startTimer(emojiType);
	}

	private synchronized void removeEmoji(EmojiType emojiId) {
		emojiRemoveTimers.remove(emojiId);
		final EmojiMenuItem removedMenuItem = emojiMenuItems.remove(emojiId);
		if (emojiMenuItems.isEmpty()) {
			showPlaceholder();
			return;
		}

		remove(removedMenuItem);

		if (emojiMenuItems.size() == 1) {
			final EmojiMenuItem menuItem = emojiMenuItems.entrySet().iterator().next().getValue();
			setIcon(menuItem.getIcon());
			setText(menuItem.getText());
			setPopupMenuVisible(false);
			setEnabled(false);

			remove(menuItem);
		}
	}

	public synchronized void clear() {
		emojiMenuItems.clear();

		removeAll();

		emojiRemoveTimers.forEach((key, value) -> value.stop());
		emojiRemoveTimers.clear();

		showPlaceholder();
	}

	private void startTimer(EmojiType emojiType) {
		final Timer timer = new Timer(EMOJI_COUNT_RESET_MILLIS, e -> removeEmoji(emojiType));
		timer.setRepeats(false);
		timer.start();
		emojiRemoveTimers.put(emojiType, timer);
	}

	private void showDropDown() {
		setIcon(PLACEHOLDER_ICON);
		setText("\u25bc");
		setEnabled(true);
	}

	private void showPlaceholder() {
		setIcon(PLACEHOLDER_ICON);
		setText(null);
		setEnabled(false);
	}

	@Override
	public void setIcon(Icon defaultIcon) {
		setDisabledIcon(defaultIcon);
		super.setIcon(defaultIcon);
	}

	private Icon getIconByType(EmojiType type) {
		String filename = "";
		switch (type) {
			case CUP:
				filename = "cup.svg";
				break;
			case THUMBS_UP:
				filename = "hand-thumbs-up.svg";
				break;
			case THUMBS_DOWN:
				filename = "hand-thumbs-down.svg";
				break;
		}
		return AwtResourceLoader.getIcon(EMOJI_PATH + filename, 20);
	}

	private static class EmojiIndicatorMenuUI extends BasicMenuUI {
		@Override
		protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
			super.paintBackground(g, menuItem, Color.GRAY);
		}
	}

	private static class EmojiMenuItem extends JMenuItem {
		private int count = 1;

		EmojiMenuItem(Icon emoji) {
			setEnabled(false);
			super.setIcon(emoji);
			setDisabledIcon(emoji);
			setText(String.valueOf(count));
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		void incrementCount() {
			++count;
			setText(String.valueOf(count));
		}
	}
}
