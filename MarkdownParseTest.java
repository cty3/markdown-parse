import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException {
        Path filename = Path.of("test-file2.md");
        String contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("https://something.com", "some-page.html"));

        filename = Path.of("test-file.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("https://something.com", "some-page.html", "hello"));

        filename = Path.of("test-file3.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of());

        filename = Path.of("test-file4.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("a link on the first line"));
    }

    @Test
    public void testSnippet1() throws IOException {
        Path filename = Path.of("snippet1.md");
        String contents = Files.readString(filename);
        assertEquals(List.of("url.com", "`google.com", "google.com", "ucsd.edu"), MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet3() throws IOException {
        Path filename = Path.of("snippet3.md");
        String contents = Files.readString(filename);
        assertEquals(List.of("https://ucsd-cse15l-w22.github.io/"), MarkdownParse.getLinks(contents));
    }


    /*
    @Test
    public void testGetLinks() {
        boolean exceptionThrown = false;
        Path filename = Path.of("test-file2.md");
        String contents;
        try {
            contents = Files.readString(filename);
        } catch (IOException e) {
            exceptionThrown = true;
        }
        assertEquals(false, exceptionThrown);
    }
    */
}