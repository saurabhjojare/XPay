import { Box } from "@mui/material";
import React from "react";
import Header from "../header/HeaderPage";
import Footer from "../footer/FooterPage";
import { PageLayoutProps } from "../../interfaces/PageLayout.types";

const PageLayout: React.FC<PageLayoutProps> = ({ header, children, footer }) => {
    return (
        <Box>
            {header || <Header />}
            <Box>
                {children}
            </Box>
            {footer || <Footer />}
        </Box >
    );
};

export default PageLayout;
