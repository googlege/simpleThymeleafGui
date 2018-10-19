package de.homedev.thymeleaf.simplegui.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.homedev.thymeleaf.simplegui.config.MessageComponent;

public abstract class AbstractController {
	private final MessageComponent messageComponent;

	public AbstractController(MessageComponent messageComponent) {
		super();
		this.messageComponent = messageComponent;
	}

	protected void addErrorMessage(final String message, final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", message);
	}

	protected void addErrorMessageAndTranslate(final String message, final RedirectAttributes redirectAttributes) {
		addErrorMessage(messageComponent.getMessage(message), redirectAttributes);
	}

	protected void addSuccessMessage(final String message, final RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("success", message);
	}

	protected void addSuccessMessageAndTranslate(final String message, final RedirectAttributes redirectAttributes) {
		addSuccessMessage(messageComponent.getMessage(message), redirectAttributes);
	}

	public MessageComponent getMessageComponent() {
		return messageComponent;
	}

}
