PDFLATEX=/usr/bin/pdflatex
SOURCE=AIsim.tex
RESULT=AIsim.pdf

$(RESULT): $(SOURCE)
	$(PDFLATEX) $(SOURCE)
	$(PDFLATEX) $(SOURCE)

clean:
	rm -f $(RESULT) *.aux *.log *.toc *.out *~
