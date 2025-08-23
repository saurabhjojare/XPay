import { Box } from "@mui/material";
import React from "react";
import Header from "../header/header.page";
import Footer from "../Footer/footer.page";
import { PageLayoutProps } from "../../interfaces/page-layout.props";

const PageLayout: React.FC<PageLayoutProps> = ({ children, header, footer }) => {
    return (
        <Box>
            {header ?? <Header />}
            <Box>
                {children}
            </Box>
            {footer ?? <Footer />}
        </Box >
    );
};

export default PageLayout;
