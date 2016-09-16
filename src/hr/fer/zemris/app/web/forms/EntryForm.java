package hr.fer.zemris.app.web.forms;

import hr.fer.zemris.app.model.BlogEntry;

import javax.servlet.http.HttpServletRequest;

/**
 * Used for creating new entries and editing old ones.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class EntryForm extends AbstractForm {

    private String id;
    private String title;
    private String text;

    /**
     * Fills the given entry with data provided through the form
     * 
     * @param entry entry to fill
     */
    public void fillEntry(BlogEntry entry) {
        if (id.isEmpty()) {
            entry.setId(null);
        } else {
            entry.setId(Long.parseLong(id));
        }
        entry.setTitle(title);
        entry.setText(text);
    }

    @Override
    public void fillFromRequest(HttpServletRequest req) {
        id = obradi(req.getParameter("id"));
        title = obradi(req.getParameter("title"));
        String _text = req.getParameter("text");
        if (_text == null) {
            _text = "";
        }
        text = _text;
    }

    /**
     * Fills the form using the entry
     * 
     * @param entry entry
     */
    public void fillFromEntry(BlogEntry entry) {
        id = obradi(entry.getId());
        title = obradi(entry.getTitle());
        text = obradi(entry.getText());
    }

    @Override
    public void validate() {
        if (title.isEmpty()) {
            errors.put("title", "Can't create an entry wihout a title.");
        }
        if (text.isEmpty()) {
            errors.put("text", "Can't create an entry without any content.");
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
